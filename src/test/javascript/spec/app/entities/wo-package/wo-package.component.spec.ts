/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpringApplicarionTestModule } from '../../../test.module';
import { WoPackageComponent } from 'app/entities/wo-package/wo-package.component';
import { WoPackageService } from 'app/entities/wo-package/wo-package.service';
import { WoPackage } from 'app/shared/model/wo-package.model';

describe('Component Tests', () => {
    describe('WoPackage Management Component', () => {
        let comp: WoPackageComponent;
        let fixture: ComponentFixture<WoPackageComponent>;
        let service: WoPackageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [WoPackageComponent],
                providers: []
            })
                .overrideTemplate(WoPackageComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(WoPackageComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WoPackageService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new WoPackage(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.woPackages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
