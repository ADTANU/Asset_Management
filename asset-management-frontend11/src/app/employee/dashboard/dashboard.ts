import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class EmployeeDashboardComponent implements OnInit {
  employeeName: string = 'Employee';
  dashboardData: any[] = [];
  uniqueDashboardData: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchEmployeeName();
    this.fetchDashboardData();
  }

  fetchEmployeeName(): void {
    this.http.get<{ username: string }>('http://localhost:8080/api/employee/profile')
      .subscribe({
        next: res => {
          this.employeeName = res.username || 'Employee';
        },
        error: err => {
          console.error('Error fetching employee name:', err);
        }
      });
  }

  fetchDashboardData(): void {
    this.http.get<any[]>('http://localhost:8080/api/employee/dashboard')
      .subscribe({
        next: data => {
          this.dashboardData = data;
          this.removeDuplicates();
        },
        error: err => {
          console.error('Error fetching dashboard data:', err);
        }
      });
  }

  removeDuplicates(): void {
    const seen = new Set();
    this.uniqueDashboardData = this.dashboardData.filter(item => {
      const key = item.id || item.assetId || JSON.stringify(item);
      if (seen.has(key)) {
        return false;
      } else {
        seen.add(key);
        return true;
      }
    });
  }

  logout(): void {
    localStorage.clear();
    window.location.href = '/';
  }
}
