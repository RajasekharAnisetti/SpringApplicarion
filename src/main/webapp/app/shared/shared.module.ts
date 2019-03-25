import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { SpringApplicarionSharedLibsModule, SpringApplicarionSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [SpringApplicarionSharedLibsModule, SpringApplicarionSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [SpringApplicarionSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpringApplicarionSharedModule {
    static forRoot() {
        return {
            ngModule: SpringApplicarionSharedModule
        };
    }
}
