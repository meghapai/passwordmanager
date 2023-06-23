import { UpdatePassComponent } from './components/update-pass/update-pass.component';
import { UserGuard } from './service/user.guard';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/user/dashboard/dashboard.component';
import { PassdataComponent } from './pages/user/passdata/passdata.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },

  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full',
  },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },

  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [UserGuard],
  },

  {
    path: 'passdata',
    component: PassdataComponent,
    canActivate: [UserGuard],
  },

  {
    path: 'updatePassword/:id',
    component: UpdatePassComponent,
    canActivate: [UserGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
