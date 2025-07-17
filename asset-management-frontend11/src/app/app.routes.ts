import { Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login';
import { RegisterComponent } from './auth/register/register';

import { EmployeeDashboardComponent } from './employee/dashboard/dashboard';
import { ViewAssetsComponent } from './employee/view-assets/view-assets';
import { RequestAssetComponent } from './employee/request-asset/request-asset';
import { RequestServiceComponent } from './employee/request-service/request-service';
import { AuditRequestComponent } from './employee/audit-request/audit-request';

import { AdminDashboardComponent } from './admin/dashboard/dashboard';
import { ManageEmployeesComponent } from './admin/manage-employees/manage-employees';
import { EditEmployeeComponent } from './admin/manage-employees/edit-employee/edit-employee';
import { ManageAssetsComponent } from './admin/manage-assets/manage-assets';
import { AuditAssets } from './admin/audit-assets/audit-assets';
import { ServiceRequestsComponent } from './admin/service-requests/service-requests';


export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Employee routes
  {
    path: 'employee/dashboard',
    component: EmployeeDashboardComponent
  },
  
  {
    path: 'employee/view-assets',
    component: ViewAssetsComponent
  },
  {
    path: 'employee/request-asset',
    component: RequestAssetComponent
  },
  {
    path: 'employee/request-service',
    component: RequestServiceComponent
  },
  {
    path: 'employee/audit-request',
    component: AuditRequestComponent
  },

  // Admin routes
  {
    path: 'admin/dashboard',
    component: AdminDashboardComponent
  },
  {
    path: 'admin/manage-employees',
    component: ManageEmployeesComponent
  },
  {
    path: 'admin/edit-employee/:id',
    component: EditEmployeeComponent
  },
  {
    path: 'admin/manage-assets',
    component: ManageAssetsComponent
  },
  {
    path: 'admin/audit-assets', // ✅ ADDED
    component: AuditAssets
  },
  { path: 'service-requests', component: ServiceRequestsComponent },
 { path: '**', redirectTo: '/admin/service-request' },


  // Catch-all
  { path: '**', redirectTo: '' }
];
