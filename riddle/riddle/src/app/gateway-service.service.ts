import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GatewayServiceService {

  constructor(private http: HttpClient) {
  }
  

  async getRiddle(id: string) {
    let params = new HttpParams().set('id', id);
    return this.http.get("https://ye40gbwvi2.execute-api.eu-central-1.amazonaws.com/v1/", { params: params }).toPromise();
  }
}
