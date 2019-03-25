import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringApplicarionSharedModule } from 'app/shared';
import {
    ShippingAddressComponent,
    ShippingAddressDetailComponent,
    ShippingAddressUpdateComponent,
    ShippingAddressDeletePopupComponent,
    ShippingAddressDeleteDialogComponent,
    shippingAddressRoute,
    shippingAddressPopupRoute
} from './';

const ENTITY_STATES = [...shippingAddressRoute, ...shippingAddressPopupRoute];

@NgModule({
    imports: [SpringApplicarionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ShippingAddressComponent,
        ShippingAddressDetailComponent,
        ShippingAddressUpdateComponent,
        ShippingAddressDeleteDialogComponent,
        ShippingAddressDeletePopupComponent
    ],
    entryComponents: [
        ShippingAddressComponent,
        ShippingAddressUpdateComponent,
        ShippingAddressDeleteDialogComponent,
        ShippingAddressDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionShippingAddressModule {}
