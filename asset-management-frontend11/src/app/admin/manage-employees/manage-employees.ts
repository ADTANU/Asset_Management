import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-manage-employees',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manage-employees.html',
  styleUrls: ['./manage-employees.css']
})
export class ManageEmployeesComponent implements OnInit {
  employees: any[] = [];

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchEmployeesFromBackend();
  }

  fetchEmployeesFromBackend(): void {
    this.http.get<any[]>('http://localhost:8080/api/employees')
      .subscribe({
        next: (res) => {
          this.employees = this.removeDuplicates(res);
        },
        error: (err) => {
          console.error('Failed to fetch employees', err);
        }
      });
  }

  removeDuplicates(employees: any[]): any[] {
    const seen = new Set<number>();
    return employees.filter(emp => {
      if (seen.has(emp.employeeId)) {
        return false;
      } else {
        seen.add(emp.employeeId);
        return true;
      }
    });
  }

  deleteEmployee(employeeId: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employees = this.employees.filter(emp => emp.employeeId !== employeeId);
    }
  }

  editEmployee(employeeId: number): void {
    this.router.navigate(['/admin/edit-employee', employeeId]);
  }
}
