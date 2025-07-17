import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth'; 

  constructor(private http: HttpClient) {}

  login(data: { email: string, password: string }) {
  return this.http.post<any>('http://localhost:8080/api/auth/login', data);
}


  register(user: any): Observable<any> {
    return this.http.post(this.baseUrl + '/register', user);
  }
}
