import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssetService {

  private baseUrl = 'http://localhost:8080/api/employee/assets';

  constructor(private http: HttpClient) {}

  getAllAssets(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  deleteAsset(assetId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${assetId}`);
  }

  getAssetById(assetId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${assetId}`);
  }

  updateAsset(assetId: number, assetData: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/update/${assetId}`, assetData);
  }

  addAsset(assetData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add`, assetData);
  }
}
