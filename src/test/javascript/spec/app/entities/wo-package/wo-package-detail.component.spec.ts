/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpringApplicarionTestModule } from '../../../test.module';
import { WoPackageDetailComponent } from 'app/entities/wo-package/wo-package-detail.component';
import { WoPackage } from 'app/shared/model/wo-package.model';

describe('Component Tests', () => {
    describe('WoPackage Management Detail Component', () => {
        let comp: WoPackageDetailComponent;
        let fixture: ComponentFixture<WoPackageDetailComponent>;
        const route = ({ data: of({ woPackage: new WoPackage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [WoPackageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(WoPackageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WoPackageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.woPackage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
