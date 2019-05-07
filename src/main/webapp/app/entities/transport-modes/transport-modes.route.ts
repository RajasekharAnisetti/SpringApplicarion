import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TransportModes } from 'app/shared/model/transport-modes.model';
import { TransportModesService } from './transport-modes.service';
import { TransportModesComponent } from './transport-modes.component';
import { TransportModesDetailComponent } from './transport-modes-detail.component';
import { TransportModesUpdateComponent } from './transport-modes-update.component';
import { TransportModesDeletePopupComponent } from './transport-modes-delete-dialog.component';
import { ITransportModes } from 'app/shared/model/transport-modes.model';

@Injectable({ providedIn: 'root' })
export class TransportModesResolve implements Resolve<ITransportModes> {
    constructor(private service: TransportModesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITransportModes> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TransportModes>) => response.ok),
                map((transportModes: HttpResponse<TransportModes>) => transportModes.body)
            );
        }
        return of(new TransportModes());
    }
}

export const transportModesRoute: Routes = [
    {
        path: '',
        component: TransportModesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransportModes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TransportModesDetailComponent,
        resolve: {
            transportModes: TransportModesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransportModes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TransportModesUpdateComponent,
        resolve: {
            transportModes: TransportModesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransportModes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TransportModesUpdateComponent,
        resolve: {
            transportModes: TransportModesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransportModes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transportModesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TransportModesDeletePopupComponent,
        resolve: {
            transportModes: TransportModesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransportModes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
