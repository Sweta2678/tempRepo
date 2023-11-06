import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { OverviewComponent } from './overview/overview.component';
import { MoqComponent } from './moq/moq.component';
import { SkuChangeComponent } from './sku-change/sku-change.component';
import { UpcValidationComponent } from './upc-validation/upc-validation.component';
import { S4UploadComponent } from './s4-upload/s4-upload.component';
import { ConfigComponent } from './config/config.component';
import { AuditreportComponent } from './auditreport/auditreport.component';
import { LandingComponent } from './landing/landing.component';

import { RestService } from './services/rest.service';
import { LoaderInterceptor } from './services/loader.interceptor';
import { AuthGuardService as AuthGuard } from './services/auth-guard.service';

import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';

@NgModule({
  declarations: [
    AppComponent,
    OverviewComponent,
    MoqComponent,
    SkuChangeComponent,
    UpcValidationComponent,
    S4UploadComponent,
    ConfigComponent,
    AuditreportComponent,
    LandingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    TableModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    CalendarModule
  ],
  providers: [
    { provide: AuthGuard },
    { provide: RestService },
    { provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
