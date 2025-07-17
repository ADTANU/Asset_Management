import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-request-service',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './request-service.html',
  styleUrls: ['./request-service.css']
})
export class RequestServiceComponent {
  serviceRequest = {
    assetNumber: '',
    assetCategory: '',
    assetModel: '',
    issueType: '',
    priorityLevel: '',
    description: '',
    employeeUsername: localStorage.getItem('username') || '',
    status: 'Pending'
  };

  message = '';
  error = '';

  constructor(private router: Router, private http: HttpClient) {}

  onSubmit() {
    this.message = '';
    this.error = '';

    this.http.post('http://localhost:8080/api/service-requests', this.serviceRequest)
      .subscribe({
        next: () => {
          this.message = 'Service request submitted successfully!';
          // Clear form fields
          this.serviceRequest.assetNumber = '';
          this.serviceRequest.assetCategory = '';
          this.serviceRequest.assetModel = '';
          this.serviceRequest.issueType = '';
          this.serviceRequest.priorityLevel = '';
          this.serviceRequest.description = '';

          setTimeout(() => {
            this.router.navigate(['/employee/dashboard']);
          }, 1000);
        },
        error: err => {
          console.error('Error submitting service request:', err);
          this.error = 'Failed to submit service request. Please try again later.';
        }
      });
  }
}
