import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringApplicarionSharedModule } from 'app/shared';
import {
    WoPackageComponent,
    WoPackageDetailComponent,
    WoPackageUpdateComponent,
    WoPackageDeletePopupComponent,
    WoPackageDeleteDialogComponent,
    woPackageRoute,
    woPackagePopupRoute
} from './';

const ENTITY_STATES = [...woPackageRoute, ...woPackagePopupRoute];

@NgModule({
    imports: [SpringApplicarionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WoPackageComponent,
        WoPackageDetailComponent,
        WoPackageUpdateComponent,
        WoPackageDeleteDialogComponent,
        WoPackageDeletePopupComponent
    ],
    entryComponents: [WoPackageComponent, WoPackageUpdateComponent, WoPackageDeleteDialogComponent, WoPackageDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionWoPackageModule {}
