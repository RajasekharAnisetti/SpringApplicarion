import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoPackage } from 'app/shared/model/wo-package.model';

type EntityResponseType = HttpResponse<IWoPackage>;
type EntityArrayResponseType = HttpResponse<IWoPackage[]>;

@Injectable({ providedIn: 'root' })
export class WoPackageService {
    public resourceUrl = SERVER_API_URL + 'api/wo-packages';

    constructor(protected http: HttpClient) {}

    create(woPackage: IWoPackage): Observable<EntityResponseType> {
        return this.http.post<IWoPackage>(this.resourceUrl, woPackage, { observe: 'response' });
    }

    update(woPackage: IWoPackage): Observable<EntityResponseType> {
        return this.http.put<IWoPackage>(this.resourceUrl, woPackage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IWoPackage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IWoPackage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
