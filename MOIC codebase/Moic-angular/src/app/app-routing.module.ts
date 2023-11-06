import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OverviewComponent } from './overview/overview.component';
import { MoqComponent } from './moq/moq.component';
import { SkuChangeComponent } from './sku-change/sku-change.component';
import { UpcValidationComponent } from './upc-validation/upc-validation.component';
import { S4UploadComponent } from './s4-upload/s4-upload.component';
import { ConfigComponent } from './config/config.component';
import { AuthGuardService as AuthGuard } from './services/auth-guard.service';
import { AuditreportComponent } from './auditreport/auditreport.component';
import { LandingComponent } from './landing/landing.component';


const routes: Routes = [
    { path: "", redirectTo: "/landing", pathMatch: "full" },
    { path: "landing", component: LandingComponent },
    { path: "overview", component: OverviewComponent,canActivate: [AuthGuard] },
    { path: "moq", component: MoqComponent,canActivate: [AuthGuard] },
    { path: "sku-change", component: SkuChangeComponent,canActivate: [AuthGuard] },
    { path: "upc-validation", component: UpcValidationComponent,canActivate: [AuthGuard] },
    { path: "s4-upload", component: S4UploadComponent,canActivate: [AuthGuard] },
    { path: "config", component: ConfigComponent,canActivate: [AuthGuard] },
    { path: "auditreport", component: AuditreportComponent,canActivate: [AuthGuard] },
    { path: "*", component: LandingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
