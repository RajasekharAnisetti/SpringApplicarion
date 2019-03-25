import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoPackage } from 'app/shared/model/wo-package.model';
import { WoPackageService } from './wo-package.service';
import { WoPackageComponent } from './wo-package.component';
import { WoPackageDetailComponent } from './wo-package-detail.component';
import { WoPackageUpdateComponent } from './wo-package-update.component';
import { WoPackageDeletePopupComponent } from './wo-package-delete-dialog.component';
import { IWoPackage } from 'app/shared/model/wo-package.model';

@Injectable({ providedIn: 'root' })
export class WoPackageResolve implements Resolve<IWoPackage> {
    constructor(private service: WoPackageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoPackage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<WoPackage>) => response.ok),
                map((woPackage: HttpResponse<WoPackage>) => woPackage.body)
            );
        }
        return of(new WoPackage());
    }
}

export const woPackageRoute: Routes = [
    {
        path: '',
        component: WoPackageComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WoPackages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: WoPackageDetailComponent,
        resolve: {
            woPackage: WoPackageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WoPackages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: WoPackageUpdateComponent,
        resolve: {
            woPackage: WoPackageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WoPackages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: WoPackageUpdateComponent,
        resolve: {
            woPackage: WoPackageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WoPackages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const woPackagePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: WoPackageDeletePopupComponent,
        resolve: {
            woPackage: WoPackageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WoPackages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
