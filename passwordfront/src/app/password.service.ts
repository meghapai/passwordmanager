import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './service/helper';

@Injectable({
  providedIn: 'root',
})
export class PasswordService {
  constructor(private http: HttpClient) {}

  //add password
  public addPassword(password: any) {
    return this.http.post(`${baseUrl}/accountPassword/add-password`, password);
  }

  //fetch password of only the logged in user
  public getPasswordUnique(userEmail: String) {
    return this.http.get(
      `${baseUrl}/accountPassword/find-password/${userEmail}`
    );
  }

  //all passwords
  public getPassword() {
    return this.http.get(`${baseUrl}/accountPassword/all`);
  }

  public deletePassword(id: Number) {
    return this.http.delete(`${baseUrl}/accountPassword/delete/${id}`);
  }

  public updatePassword(id: Number, password: any) {
    return this.http.put(
      `${baseUrl}/accountPassword/updatePassword/${id}`,
      password
    );
  }

  //fetch password by id
  public getPasswordById(id: Number) {
    return this.http.get(`${baseUrl}/accountPassword/password-by-id/${id}`);
  }
}
