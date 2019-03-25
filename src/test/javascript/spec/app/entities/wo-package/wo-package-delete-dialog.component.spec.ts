/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SpringApplicarionTestModule } from '../../../test.module';
import { WoPackageDeleteDialogComponent } from 'app/entities/wo-package/wo-package-delete-dialog.component';
import { WoPackageService } from 'app/entities/wo-package/wo-package.service';

describe('Component Tests', () => {
    describe('WoPackage Management Delete Component', () => {
        let comp: WoPackageDeleteDialogComponent;
        let fixture: ComponentFixture<WoPackageDeleteDialogComponent>;
        let service: WoPackageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [WoPackageDeleteDialogComponent]
            })
                .overrideTemplate(WoPackageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WoPackageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WoPackageService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
