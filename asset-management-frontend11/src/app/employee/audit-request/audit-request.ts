import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-audit-request',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './audit-request.html',
  styleUrls: ['./audit-request.css']
})
export class AuditRequestComponent {
  assetName: string = '';
  assetCategory: string = '';
  employeeName: string = '';
  assetID: string = '';
  assetStatus: string = '';
  priorityLevel: string = '';
  auditRequestType: string = '';
  auditDate: string = '';
  comments: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  submitAuditRequest(): void {
    this.successMessage = '';
    this.errorMessage = '';

    const auditRequestPayload = {
      assetName: this.assetName,
      assetCategory: this.assetCategory,
      employeeName: this.employeeName,
      assetID: this.assetID,
      assetStatus: this.assetStatus,
      priorityLevel: this.priorityLevel,
      auditRequestType: this.auditRequestType,
      auditDate: this.auditDate,
      comments: this.comments
    };

    this.http.post('http://localhost:8080/api/audit-requests', auditRequestPayload)
      .subscribe({
        next: () => {
          this.successMessage = 'Audit request submitted successfully!';
          this.clearForm();

          setTimeout(() => {
            this.router.navigate(['/employee/dashboard']);
          }, 1500);
        },
        error: (err) => {
          this.errorMessage = 'Failed to submit audit request. Please try again later.';
          console.error('Audit request submission error:', err);
        }
      });
  }

  private clearForm(): void {
    this.assetName = '';
    this.assetCategory = '';
    this.employeeName = '';
    this.assetID = '';
    this.assetStatus = '';
    this.priorityLevel = '';
    this.auditRequestType = '';
    this.auditDate = '';
    this.comments = '';
  }
}
