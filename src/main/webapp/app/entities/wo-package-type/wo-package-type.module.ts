import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringApplicarionSharedModule } from 'app/shared';
import {
    WoPackageTypeComponent,
    WoPackageTypeDetailComponent,
    WoPackageTypeUpdateComponent,
    WoPackageTypeDeletePopupComponent,
    WoPackageTypeDeleteDialogComponent,
    woPackageTypeRoute,
    woPackageTypePopupRoute
} from './';

const ENTITY_STATES = [...woPackageTypeRoute, ...woPackageTypePopupRoute];

@NgModule({
    imports: [SpringApplicarionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WoPackageTypeComponent,
        WoPackageTypeDetailComponent,
        WoPackageTypeUpdateComponent,
        WoPackageTypeDeleteDialogComponent,
        WoPackageTypeDeletePopupComponent
    ],
    entryComponents: [
        WoPackageTypeComponent,
        WoPackageTypeUpdateComponent,
        WoPackageTypeDeleteDialogComponent,
        WoPackageTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionWoPackageTypeModule {}
