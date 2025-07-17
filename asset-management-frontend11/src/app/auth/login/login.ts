import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  // Handle form submission
  onSubmit(): void {
    // Clear any existing error messages
    this.errorMessage = '';

    // Validate input fields
    if (!this.email || !this.password) {
      this.errorMessage = 'Email and password are required.';
      return;
    }

    // Call the login service to authenticate user
    this.authService.login({ email: this.email, password: this.password }).subscribe({
      next: (res: any) => {
        // Store token and role in localStorage for session management
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role);  // Store role for role-based routing

        // Redirect user based on their role
        if (res.role === 'ROLE_ADMIN') {
          this.router.navigate(['/admin/dashboard']);
        } else if (res.role === 'ROLE_EMPLOYEE') {
          this.router.navigate(['/employee/dashboard']);
        } else {
          this.errorMessage = 'Unexpected role. Please contact support.';
        }
      },
      error: (err) => {
        // Handle errors during login (invalid credentials, etc.)
        if (err.status === 401) {
          this.errorMessage = 'Invalid credentials. Please try again.';
        } else {
          this.errorMessage = 'An unexpected error occurred. Please try again later.';
        }
        console.error('Login failed:', err);
      }
    });
  }

  // Redirect to register page
  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
