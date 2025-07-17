import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class AdminDashboardComponent implements OnInit {
  adminName: string = 'Admin';
  dashboardData: any[] = [];
  uniqueData: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadAdminName();
    this.loadDashboardData();
  }

  loadAdminName(): void {
    this.http.get<{ name: string }>('http://localhost:8080/api/admin/details').subscribe({
      next: (res) => {
        this.adminName = res?.name || 'Admin';
      },
      error: (err) => {
        console.error('Failed to fetch admin name:', err);
      }
    });
  }

  loadDashboardData(): void {
    this.http.get<any[]>('http://localhost:8080/api/admin/dashboard').subscribe({
      next: (res) => {
        this.dashboardData = res;
        this.removeDuplicates();
      },
      error: (err) => {
        console.error('Failed to fetch dashboard data:', err);
      }
    });
  }

  removeDuplicates(): void {
    const seen = new Set();
    this.uniqueData = this.dashboardData.filter(item => {
      const key = item.assetId || item.id || JSON.stringify(item);
      if (seen.has(key)) return false;
      seen.add(key);
      return true;
    });
  }

  logout(): void {
    localStorage.clear();
    window.location.href = '/login';
  }
}
