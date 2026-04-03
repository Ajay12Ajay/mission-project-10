import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private httpClient: HttpClient) {

  }

  post(endpoint: any, bean: any, callback: any) {
    return this.httpClient.post(endpoint, bean).subscribe((data) => {
      callback(data);
    }, (error) => {
      this.handleError(error, callback);
    });
  }

  get(endpoint: any, callback: any) {
    return this.httpClient.get(endpoint).subscribe((data) => {
      callback(data);
    }, (error) => {
      this.handleError(error, callback);
    });
  }

  private handleError(error: any, callback: any) {
    if (error.status === 503) {
      callback({
        success: false,
        result: {
          message: error.error?.result?.message
        }
      })
    }
  }



}