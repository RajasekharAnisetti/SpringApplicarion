import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'country',
                loadChildren: './country/country.module#SpringApplicarionCountryModule'
            },
            {
                path: 'province',
                loadChildren: './province/province.module#SpringApplicarionProvinceModule'
            },
            {
                path: 'city',
                loadChildren: './city/city.module#SpringApplicarionCityModule'
            },
            {
                path: 'shipping-address',
                loadChildren: './shipping-address/shipping-address.module#SpringApplicarionShippingAddressModule'
            },
            {
                path: 'wo-package',
                loadChildren: './wo-package/wo-package.module#SpringApplicarionWoPackageModule'
            },
            {
                path: 'wo-package-type',
                loadChildren: './wo-package-type/wo-package-type.module#SpringApplicarionWoPackageTypeModule'
            },
            {
                path: 'wo-work-order',
                loadChildren: './wo-work-order/wo-work-order.module#SpringApplicarionWoWorkOrderModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#SpringApplicarionCountryModule'
            },
            {
                path: 'province',
                loadChildren: './province/province.module#SpringApplicarionProvinceModule'
            },
            {
                path: 'city',
                loadChildren: './city/city.module#SpringApplicarionCityModule'
            },
            {
                path: 'shipping-address',
                loadChildren: './shipping-address/shipping-address.module#SpringApplicarionShippingAddressModule'
            },
            {
                path: 'wo-package',
                loadChildren: './wo-package/wo-package.module#SpringApplicarionWoPackageModule'
            },
            {
                path: 'wo-package-type',
                loadChildren: './wo-package-type/wo-package-type.module#SpringApplicarionWoPackageTypeModule'
            },
            {
                path: 'wo-work-order',
                loadChildren: './wo-work-order/wo-work-order.module#SpringApplicarionWoWorkOrderModule'
            },
            {
                path: 'transport-modes',
                loadChildren: './transport-modes/transport-modes.module#SpringApplicarionTransportModesModule'
            },
            {
                path: 'wo-customs-brokerage',
                loadChildren: './wo-customs-brokerage/wo-customs-brokerage.module#SpringApplicarionWoCustomsBrokerageModule'
            },
            {
                path: 'wo-transport-modes',
                loadChildren: './wo-transport-modes/wo-transport-modes.module#SpringApplicarionWoTransportModesModule'
            },
            {
                path: 'wo-work-order',
                loadChildren: './wo-work-order/wo-work-order.module#SpringApplicarionWoWorkOrderModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionEntityModule {}
