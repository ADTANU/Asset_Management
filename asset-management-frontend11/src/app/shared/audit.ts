import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AuditAsset {
  auditRequestId: number;
  assetId: number;
  userId: number;
  auditStatus: string;
  requestDate: string;
  responseDate?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuditService {
  private apiUrl = 'http://localhost:8080/api/employee/audit';

  constructor(private http: HttpClient) {}

  getAuditAssets(): Observable<AuditAsset[]> {
    return this.http.get<AuditAsset[]>(this.apiUrl);
  }

  submitAuditRequest(auditRequest: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, auditRequest);
  }
}
