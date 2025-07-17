import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-assets',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-assets.html',
  styleUrls: ['./view-assets.css']
})
export class ViewAssetsComponent implements OnInit {
  assets: any[] = [];
  filteredAssets: any[] = [];
  selectedCategory: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.fetchAssetsFromBackend();
  }

  fetchAssetsFromBackend() {
    this.http.get<any[]>('http://localhost:8080/api/assets')
      .subscribe({
        next: (data) => {
          // Remove duplicates based on assetNo
          const seen = new Set();
          this.assets = data.filter(asset => {
            const key = asset.assetNo;
            if (seen.has(key)) {
              return false;
            }
            seen.add(key);
            return true;
          });
          this.filteredAssets = [...this.assets];
        },
        error: (error) => {
          console.error('Error fetching assets:', error);
          this.assets = [];
          this.filteredAssets = [];
        }
      });
  }

  onCategoryChange() {
    if (!this.selectedCategory) {
      this.filteredAssets = [...this.assets];
    } else {
      this.filteredAssets = this.assets.filter(asset => asset.assetCategory === this.selectedCategory);
    }
  }

  goHome() {
    this.router.navigate(['/employee/dashboard']);
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'Assigned': return 'badge-success';
      case 'Under Service': return 'badge-danger';
      case 'Available': return 'badge-warning';
      default: return 'badge-info';
    }
  }
}
