/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { WoPackageService } from 'app/entities/wo-package/wo-package.service';
import { IWoPackage, WoPackage } from 'app/shared/model/wo-package.model';

describe('Service Tests', () => {
    describe('WoPackage Service', () => {
        let injector: TestBed;
        let service: WoPackageService;
        let httpMock: HttpTestingController;
        let elemDefault: IWoPackage;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(WoPackageService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new WoPackage(0, 'AAAAAAA', 0, 0, 0, 0, 0, 'AAAAAAA', 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a WoPackage', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new WoPackage(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a WoPackage', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        length: 1,
                        width: 1,
                        height: 1,
                        weight: 1,
                        cubedWeight: 1,
                        trackingNumber: 'BBBBBB',
                        codValue: 1,
                        insuranceAmount: 1,
                        oid: 1,
                        position: 1,
                        freightClass: 'BBBBBB',
                        type: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of WoPackage', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        length: 1,
                        width: 1,
                        height: 1,
                        weight: 1,
                        cubedWeight: 1,
                        trackingNumber: 'BBBBBB',
                        codValue: 1,
                        insuranceAmount: 1,
                        oid: 1,
                        position: 1,
                        freightClass: 'BBBBBB',
                        type: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a WoPackage', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
