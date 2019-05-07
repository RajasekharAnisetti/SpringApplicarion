import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringApplicarionSharedModule } from 'app/shared';
import {
    WoCustomsBrokerageComponent,
    WoCustomsBrokerageDetailComponent,
    WoCustomsBrokerageUpdateComponent,
    WoCustomsBrokerageDeletePopupComponent,
    WoCustomsBrokerageDeleteDialogComponent,
    woCustomsBrokerageRoute,
    woCustomsBrokeragePopupRoute
} from './';

const ENTITY_STATES = [...woCustomsBrokerageRoute, ...woCustomsBrokeragePopupRoute];

@NgModule({
    imports: [SpringApplicarionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WoCustomsBrokerageComponent,
        WoCustomsBrokerageDetailComponent,
        WoCustomsBrokerageUpdateComponent,
        WoCustomsBrokerageDeleteDialogComponent,
        WoCustomsBrokerageDeletePopupComponent
    ],
    entryComponents: [
        WoCustomsBrokerageComponent,
        WoCustomsBrokerageUpdateComponent,
        WoCustomsBrokerageDeleteDialogComponent,
        WoCustomsBrokerageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionWoCustomsBrokerageModule {}
