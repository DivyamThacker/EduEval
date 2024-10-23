import { Routes } from '@angular/router';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { BasicInformationComponent } from './form-tabs/basic-information/basic-information.component';
import { BasicInfoComponent } from './form-tabs/basic-information/basic-info/basic-info.component';
import { ContactDetailsComponent } from './form-tabs/basic-information/contact-details/contact-details.component';
import { RecognitionDetailsComponent } from './form-tabs/basic-information/recognition-details/recognition-details.component';
import { AreaLocationComponent } from './form-tabs/basic-information/area-location/area-location.component';
import { CollegeInfoComponent } from './form-tabs/academic-information/college-info/college-info.component';

export const routes: Routes = [
    { path: '', redirectTo: '/basic-information', pathMatch: 'full' },
    { path: 'file-upload', component: FileUploadComponent },
    { path: 'basic-information', redirectTo: 'basic-information/basic-info', pathMatch: 'full' },
    {
        path: 'basic-information',
        component: BasicInformationComponent,
        children: [
            { path: 'basic-info', component: BasicInfoComponent },
            { path: 'contact-details', component: ContactDetailsComponent },
          { path: 'recognition-details', component: RecognitionDetailsComponent },
          { path: 'area-location', component: AreaLocationComponent }
        ]
      },
    { path: 'academic-information', redirectTo: 'academic-information/college-info', pathMatch: 'full' },
    {
        path: 'basic-information',
        component: BasicInformationComponent,
        children: [
            { path: 'college-info', component: CollegeInfoComponent },
            { path: 'teaching-faculty', component: CollegeInfoComponent },
            { path: 'non-teaching-staff', component: CollegeInfoComponent },
            { path: 'student-details', component: CollegeInfoComponent },
            { path: 'extra-uni-details', component: CollegeInfoComponent }
        ]
      },
    // { path: 'about', component: AboutComponent },
    // { path: 'contact', component: ContactComponent },
    // { path: '**', component: PageNotFoundComponent }
];
