import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  user = {
    name: '',
    email: '',
    password: '',
    role: 'ROLE_EMPLOYEE',
    gender: '',
    contactNumber: '',
    address: ''
  };

  errorMessage = '';
  successMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister(): void {
    this.authService.register(this.user).subscribe({
      next: (res) => {
        console.log('✅ Registration response:', res);
        this.successMessage = 'Registered successfully!';
        this.router.navigate(['/']); // Redirect to login
      },
      error: (err) => {
        console.error('❌ Registration failed:', err);
        this.errorMessage = 'Registration failed. Try again.';
      }
    });
  }
}
