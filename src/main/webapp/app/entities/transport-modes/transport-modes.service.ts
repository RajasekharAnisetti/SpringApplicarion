import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransportModes } from 'app/shared/model/transport-modes.model';

type EntityResponseType = HttpResponse<ITransportModes>;
type EntityArrayResponseType = HttpResponse<ITransportModes[]>;

@Injectable({ providedIn: 'root' })
export class TransportModesService {
    public resourceUrl = SERVER_API_URL + 'api/transport-modes';

    constructor(protected http: HttpClient) {}

    create(transportModes: ITransportModes): Observable<EntityResponseType> {
        return this.http.post<ITransportModes>(this.resourceUrl, transportModes, { observe: 'response' });
    }

    update(transportModes: ITransportModes): Observable<EntityResponseType> {
        return this.http.put<ITransportModes>(this.resourceUrl, transportModes, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransportModes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransportModes[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
