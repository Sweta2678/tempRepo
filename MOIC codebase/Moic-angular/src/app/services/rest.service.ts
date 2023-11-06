import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class RestService {

  REST_BASE_URL = environment.baseUrl;
  

  constructor(private http: HttpClient) { }

  // common method for header set in get and post api calls
  
  customHeaders = new HttpHeaders({ 
    'X-XSS-Protection': '1; mode=block',
    'X-Content-Type-Options': 'nosniff',
    'Content-Security-Policy': 'default-src ' + "'self'",
    'Access-Control-Allow-Methods': 'POST, GET',
    'Access-Control-Max-Age': '3600',
    'Strict-Transport-Security': 'max-age=31536000; includeSubDomains;'
   })

  // config screen data apis

  getConfigData() {
    let dataUrl = this.REST_BASE_URL + 'getConfigDefaultData';
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }
  updateConfigData(data, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'updateConfigData?token=' + sessionStorageValue;
    return this.http.post<any>(dataUrl, data, { headers: this.customHeaders });
  }
  uploadCustomerFile(files: File[], sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'uploadCustomerData?token=' + sessionStorageValue;
    var formData = new FormData();
    Array.from(files).forEach(f => formData.append("file", f));
    return this.http.post<any>(dataUrl, formData, { headers: this.customHeaders });
  }

  // MOQ screen data apis

  getMoqData(season, channel) {
    let dataUrl = this.REST_BASE_URL + 'getMOQDefaultData?season=' + season + '&channel=' + channel;
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }
  updateMoqData(data, season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'updateMOQData?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    return this.http.post<any>(dataUrl, data, { headers: this.customHeaders });
  }
  uploadMoqList(files: File[], season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'uploadMOQList?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    var formData = new FormData();
    Array.from(files).forEach(f => formData.append("file", f));
    return this.http.post<any>(dataUrl, formData, { headers: this.customHeaders });
  }

  uploadPreBuyList(files: File[], season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'uploadPreBuyList?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    var formData = new FormData();
    Array.from(files).forEach(f => formData.append("file", f));
    return this.http.post<any>(dataUrl, formData, { headers: this.customHeaders });
  }

  // SKU change screen data apis

  getSkuChangeData(season, channel) {
    let dataUrl = this.REST_BASE_URL + 'getSKUChangeDefaultData?season=' + season + '&channel=' + channel
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }
  updateSkuData(data, season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'updateSkuChangeData?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    return this.http.post<any>(dataUrl, data, { headers: this.customHeaders });
  }
  uploadSkuList(files: File[], season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'uploadSKUChangeList?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    var formData = new FormData();
    Array.from(files).forEach(f => formData.append("file", f));
    return this.http.post<any>(dataUrl, formData, { headers: this.customHeaders });
  }

  // Overview screen data apis

  getOverviewData(sessionStrValue, season, channel, seasonArc, channelArc) {
    let dataUrl = this.REST_BASE_URL + 'getOverviewDefaultData?season=' + season + '&channel=' + channel;
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }

  // Upload to S4 screen data apis

  getUploadToS4Data() {
    let dataUrl = this.REST_BASE_URL + 'getSapDefaultData';
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }

  uploadToS4(season, channel, sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'sapUploadBySeasonAndChannel?season=' + season + '&channel=' + channel + '&token=' + sessionStorageValue;
    //return this.http.post<any>(dataUrl, season, channel);
    return this.http.post<any>(dataUrl, { headers: this.customHeaders });
  }

  // UPC validation screen data apis

  getUpcValData() {
    let dataUrl = this.REST_BASE_URL + 'getUPCValidationDefaultData';
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }

  // Audit report screen data apis

  getUserId() {
    let dataUrl = this.REST_BASE_URL + 'getUserAuditDefaultData';
    return this.http.get<any>(dataUrl, { headers: this.customHeaders });
  }

  // to get roles and landing page

  getRoleAndLandingPage(sessionStorageValue) {
    let dataUrl = this.REST_BASE_URL + 'getRolePermissions?token=' + sessionStorageValue;
    return this.http.post<any>(dataUrl, sessionStorageValue, { headers: this.customHeaders });
  }
}