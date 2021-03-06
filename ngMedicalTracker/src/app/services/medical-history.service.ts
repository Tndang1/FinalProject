import { Medication } from './../models/medication';
import { MedicalHistory } from './../models/medical-history';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MedicalHistoryService {

  // private baseUrl = 'http://localhost:8090/';
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/medicalHistory';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    const credentials = this.auth.getCredentials();

    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }

  show(medHisId: number): Observable<MedicalHistory> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<MedicalHistory>(`${this.url}/${medHisId}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('PatientService.index(): Error retrieving patient ' + medHisId);
      })
    );
  }
  updateMedHis(medicalHistory: MedicalHistory):Observable<MedicalHistory>{
    const httpOptions = this.getHttpOptions();

    return this.http.put<MedicalHistory>(`${this.url}/${medicalHistory.id}`, medicalHistory, httpOptions).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError('medical History service failed to update')
      })
    )
  }
  createMedHis(medicalHistory: MedicalHistory):Observable<MedicalHistory>{
    const httpOptions = this.getHttpOptions();

    return this.http.post<MedicalHistory>(this.url, medicalHistory, httpOptions).pipe(
      catchError((err:any) => {
        console.error(err);
        return throwError('medical History service failed to create')
      })
    )
  }
  deleteMedHis(medHisId: number): Observable<boolean>{
    const httpOptions = this.getHttpOptions();

    return this.http.delete<boolean>(`${this.url}/${medHisId}`, httpOptions).pipe(
      catchError((err:any)=> {
        console.error(err);
        return throwError('medical History service failed to delete')
      })
    )
  }
  getDxMedications(medHisId: number): Observable<Medication[]> {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Medication[]>(`${this.url}/${medHisId}/medications`, httpOptions).pipe(
      catchError((err:any)=> {
        console.error(err);
        return throwError('Medications service failed to display')
      })
    )
  }
  }
