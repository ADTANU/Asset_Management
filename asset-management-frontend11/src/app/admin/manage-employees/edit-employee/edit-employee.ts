import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-employee',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit-employee.html',
  styleUrls: ['./edit-employee.css']
})
export class EditEmployeeComponent implements OnInit {
  employeeForm: FormGroup;
  employees: any[] = []; 
  employeeId: number | null = null;

  constructor(
    private fb: FormBuilder,
    public router: Router,
    private route: ActivatedRoute,
    private http: HttpClient
  ) {
    this.employeeForm = this.fb.group({
      username: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.employeeId = +params['id'];
      this.fetchEmployeesFromBackend();
    });
  }

  fetchEmployeesFromBackend(): void {
    this.http.get<any[]>('http://localhost:8080/api/employees') 
      .subscribe({
        next: (res) => {
          this.employees = this.removeDuplicates(res);
          this.loadEmployeeData();
        },
        error: (err) => {
          console.error('Error fetching employees', err);
        }
      });
  }

  // Remove duplicate employees by id
  removeDuplicates(employees: any[]): any[] {
    const seen = new Set();
    return employees.filter(emp => {
      if (seen.has(emp.id)) {
        return false;
      } else {
        seen.add(emp.id);
        return true;
      }
    });
  }

  loadEmployeeData(): void {
    const employee = this.employees.find(emp => emp.id === this.employeeId);
    if (employee) {
      this.employeeForm.patchValue({
        username: employee.username,
        fullName: employee.fullName,
        email: employee.email,
        role: employee.role
      });
    }
  }

  onSubmit(): void {
    if (this.employeeForm.valid && this.employeeId !== null) {
      const updatedEmployee = { id: this.employeeId, ...this.employeeForm.value };
      const index = this.employees.findIndex(emp => emp.id === this.employeeId);
      if (index !== -1) {
        this.employees[index] = updatedEmployee;
        console.log('Updated Employee:', updatedEmployee);
        this.router.navigate(['/admin/manage-employees']);
      }
    }
  }
}
