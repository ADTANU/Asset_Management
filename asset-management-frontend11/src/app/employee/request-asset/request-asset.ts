import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-request-asset',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './request-asset.html',
  styleUrls: ['./request-asset.css']
})
export class RequestAssetComponent {
  assetRequest = {
    employeeUsername: localStorage.getItem('username') || '',
    assetType: '',
    assetCategory: '',
    priorityLevel: '',
    reason: '',
    justification: '',
    employeeId: '',
    status: 'Pending'
  };
  message = '';
  error = '';

  constructor(private router: Router, private http: HttpClient) {}

  onSubmit() {
    this.message = '';
    this.error = '';

    // Call backend API to submit asset request
    this.http.post('http://localhost:8080/api/asset-requests', this.assetRequest)
      .subscribe({
        next: (res) => {
          this.message = 'Request submitted successfully!';
          // Clear the form fields
          this.assetRequest.assetType = '';
          this.assetRequest.assetCategory = '';
          this.assetRequest.priorityLevel = '';
          this.assetRequest.reason = '';
          this.assetRequest.justification = '';
          this.assetRequest.employeeId = '';

          // Redirect after a delay to show success message
          setTimeout(() => {
            this.router.navigate(['/employee/dashboard']);
          }, 2000);
        },
        error: (err) => {
          console.error('Error submitting request:', err);
          this.error = 'Failed to submit request. Please try again later.';
        }
      });
  }
}
