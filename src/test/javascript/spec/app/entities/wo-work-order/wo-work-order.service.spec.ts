/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WoWorkOrderService } from 'app/entities/wo-work-order/wo-work-order.service';
import { IWoWorkOrder, WoWorkOrder } from 'app/shared/model/wo-work-order.model';

describe('Service Tests', () => {
    describe('WoWorkOrder Service', () => {
        let injector: TestBed;
        let service: WoWorkOrderService;
        let httpMock: HttpTestingController;
        let elemDefault: IWoWorkOrder;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(WoWorkOrderService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new WoWorkOrder(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateCreated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a WoWorkOrder', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateCreated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateCreated: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new WoWorkOrder(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a WoWorkOrder', async () => {
                const returnedFromService = Object.assign(
                    {
                        customsBrokerName: 'BBBBBB',
                        customsContactName: 'BBBBBB',
                        customsCurrency: 'BBBBBB',
                        customsPhoneNumber: 'BBBBBB',
                        customsValue: 1,
                        fromAirportCode: 'BBBBBB',
                        fromLocationType: 1,
                        jobContentDescr: 'BBBBBB',
                        shipQuantity: 1,
                        jobOriginalCost: 1,
                        quotedAmount: 'BBBBBB',
                        assignTo: 'BBBBBB',
                        toCompany: 'BBBBBB',
                        toLocationType: 1,
                        dateCreated: currentDate.format(DATE_TIME_FORMAT),
                        jobNumber: 'BBBBBB',
                        jobCustomer: 'BBBBBB',
                        serviceType: 1,
                        jobSales: 'BBBBBB',
                        woRequestType: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateCreated: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of WoWorkOrder', async () => {
                const returnedFromService = Object.assign(
                    {
                        customsBrokerName: 'BBBBBB',
                        customsContactName: 'BBBBBB',
                        customsCurrency: 'BBBBBB',
                        customsPhoneNumber: 'BBBBBB',
                        customsValue: 1,
                        fromAirportCode: 'BBBBBB',
                        fromLocationType: 1,
                        jobContentDescr: 'BBBBBB',
                        shipQuantity: 1,
                        jobOriginalCost: 1,
                        quotedAmount: 'BBBBBB',
                        assignTo: 'BBBBBB',
                        toCompany: 'BBBBBB',
                        toLocationType: 1,
                        dateCreated: currentDate.format(DATE_TIME_FORMAT),
                        jobNumber: 'BBBBBB',
                        jobCustomer: 'BBBBBB',
                        serviceType: 1,
                        jobSales: 'BBBBBB',
                        woRequestType: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateCreated: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a WoWorkOrder', async () => {
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
