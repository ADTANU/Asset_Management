import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-manage-assets',
  templateUrl: './manage-assets.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./manage-assets.css'],
  standalone: true
})
export class ManageAssetsComponent implements OnInit {
  assets: any[] = [];
  uniqueAssets: any[] = [];

  selectedAsset: any | null = null;
  showModal: boolean = false;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchAssetsFromBackend();
  }

  fetchAssetsFromBackend(): void {
    this.http.get<any[]>('http://localhost:8080/api/assets')
      .subscribe({
        next: (res) => {
          this.assets = res;
          this.removeDuplicates();
        },
        error: (err) => {
          console.error('Error fetching assets', err);
        }
      });
  }

  removeDuplicates(): void {
    const seen = new Set();
    this.uniqueAssets = this.assets.filter(asset => {
      const key = asset.assetId;
      if (seen.has(key)) {
        return false;
      } else {
        seen.add(key);
        return true;
      }
    });
  }

  editAsset(assetId: number): void {
    this.selectedAsset = this.uniqueAssets.find(asset => asset.assetId === assetId) || null;
    if (this.selectedAsset) {
      this.showModal = true;
    }
  }

  saveChanges(): void {
    if (this.selectedAsset) {
      const index = this.uniqueAssets.findIndex(asset => asset.assetId === this.selectedAsset.assetId);
      if (index !== -1) {
        this.uniqueAssets[index] = { ...this.selectedAsset };
        console.log('Asset updated:', this.selectedAsset);
        this.closeModal();
      }
    }
  }

  closeModal(): void {
    this.showModal = false;
  }

  deleteAsset(assetId: number): void {
    if (confirm('Are you sure you want to delete this asset?')) {
      this.uniqueAssets = this.uniqueAssets.filter(asset => asset.assetId !== assetId);
      console.log(`Asset with ID ${assetId} deleted`);
    }
  }
}
