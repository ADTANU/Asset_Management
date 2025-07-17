import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-service-requests',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './service-requests.html',
  styleUrls: ['./service-requests.css']
})
export class ServiceRequestsComponent implements OnInit {
  serviceRequests: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchServiceRequests();
  }

  fetchServiceRequests(): void {
    this.http.get<any[]>('http://localhost:8080/api/service-requests') 
      .subscribe({
        next: (data) => {
          this.serviceRequests = this.removeDuplicates(data);
        },
        error: (err) => {
          console.error('Failed to fetch service requests', err);
        }
      });
  }

  removeDuplicates(requests: any[]): any[] {
    const seen = new Set<number>();
    return requests.filter(req => {
      if (seen.has(req.requestId)) {
        return false;
      }
      seen.add(req.requestId);
      return true;
    });
  }

  viewDetails(requestId: number): void {
    console.log('Viewing details of request:', requestId);
  }

  deleteRequest(requestId: number): void {
    if (confirm('Are you sure you want to delete this request?')) {
      this.serviceRequests = this.serviceRequests.filter(req => req.requestId !== requestId);
    }
  }
}
