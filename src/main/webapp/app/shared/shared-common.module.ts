import { NgModule } from '@angular/core';

import { SpringApplicarionSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [SpringApplicarionSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [SpringApplicarionSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class SpringApplicarionSharedCommonModule {}
