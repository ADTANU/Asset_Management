import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Header } from './common/header/header';
import { Footer } from './common/footer/footer';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, Footer , FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'asset-management-frontend11';
}
