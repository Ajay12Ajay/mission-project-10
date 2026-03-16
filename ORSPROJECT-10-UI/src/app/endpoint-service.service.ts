import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EndpointServiceService {

  constructor() { }

  public SERVER_URL = "http://localhost:8080";
  public ROLE = this.SERVER_URL + '/Role';
  public USER = this.SERVER_URL + '/User';
  public COLLEGE = this.SERVER_URL + '/College';
  public COURSE = this.SERVER_URL + '/Course';
  public SUBJECT = this.SERVER_URL + '/Subject';
  public STUDENT = this.SERVER_URL + '/Student';
  public MARKSHEET = this.SERVER_URL + '/Marksheet';
  public TIMETABLE = this.SERVER_URL + '/Timetable';
  public FACULTY = this.SERVER_URL + '/Faculty';
}
