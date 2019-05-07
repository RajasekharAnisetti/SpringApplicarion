import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringApplicarionSharedModule } from 'app/shared';
import {
    TransportModesComponent,
    TransportModesDetailComponent,
    TransportModesUpdateComponent,
    TransportModesDeletePopupComponent,
    TransportModesDeleteDialogComponent,
    transportModesRoute,
    transportModesPopupRoute
} from './';

const ENTITY_STATES = [...transportModesRoute, ...transportModesPopupRoute];

@NgModule({
    imports: [SpringApplicarionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransportModesComponent,
        TransportModesDetailComponent,
        TransportModesUpdateComponent,
        TransportModesDeleteDialogComponent,
        TransportModesDeletePopupComponent
    ],
    entryComponents: [
        TransportModesComponent,
        TransportModesUpdateComponent,
        TransportModesDeleteDialogComponent,
        TransportModesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionTransportModesModule {}
