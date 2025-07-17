import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuditAsset } from '../../shared/audit';

@Component({
  selector: 'app-audit-assets',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './audit-assets.html',
  styleUrls: ['./audit-assets.css']
})
export class AuditAssets implements OnInit {
  uniqueAssets: AuditAsset[] = [];
  selectedAsset: AuditAsset | null = null;
  showModal: boolean = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchAuditAssets();
  }

  fetchAuditAssets(): void {
    this.http.get<AuditAsset[]>('http://localhost:8080/api/audit/all').subscribe({
      next: (data) => {
        const seenIds = new Set<number>();
        this.uniqueAssets = data.filter(asset => {
          if (seenIds.has(asset.auditRequestId)) {
            return false;
          }
          seenIds.add(asset.auditRequestId);
          return true;
        });
      },
      error: (err) => {
        console.error('Error fetching audit assets:', err);
      }
    });
  }

  openEditModal(asset: AuditAsset): void {
    this.selectedAsset = { ...asset };
    this.showModal = true;
  }

  saveChanges(): void {
    if (this.selectedAsset) {
      const index = this.uniqueAssets.findIndex(a => a.auditRequestId === this.selectedAsset!.auditRequestId);
      if (index !== -1) {
        this.uniqueAssets[index] = { ...this.selectedAsset };
        console.log('Updated Asset (frontend only):', this.selectedAsset);
        this.closeModal();
      }
    }
  }

  closeModal(): void {
    this.showModal = false;
  }

  deleteAsset(auditRequestId: number): void {
    this.uniqueAssets = this.uniqueAssets.filter(asset => asset.auditRequestId !== auditRequestId);
  }
}
