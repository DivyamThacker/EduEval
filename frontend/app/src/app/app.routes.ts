import { Routes } from '@angular/router';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { BasicInformationComponent } from './form-tabs/basic-information/basic-information.component';
import { BasicInfoComponent } from './form-tabs/basic-information/basic-info/basic-info.component';
import { ContactDetailsComponent } from './form-tabs/basic-information/contact-details/contact-details.component';
import { RecognitionDetailsComponent } from './form-tabs/basic-information/recognition-details/recognition-details.component';
import { AreaLocationComponent } from './form-tabs/basic-information/area-location/area-location.component';
import { CollegeInfoComponent } from './form-tabs/academic-information/college-info/college-info.component';
import { AcademicInformationComponent } from './form-tabs/academic-information/academic-information.component';
import { ProgramDetialsComponent } from './form-tabs/extended-profile/program-detials/program-detials.component';
import { ExtendedProfileComponent } from './form-tabs/extended-profile/extended-profile.component';
import { TeachingFacultyComponent } from './form-tabs/academic-information/teaching-faculty/teaching-faculty.component';
import { StaffComponent } from './form-tabs/academic-information/staff/staff.component';
import { ExtraDetailsComponent } from './form-tabs/academic-information/extra-details/extra-details.component';
import { EnrollmentDetailsComponent } from './form-tabs/academic-information/enrollment-details/enrollment-details.component';
import { StudentDetailsComponent } from './form-tabs/extended-profile/student-details/student-details.component';
import { AcademicDetailsComponent } from './form-tabs/extended-profile/academic-details/academic-details.component';
import { InstitutionDetailsComponent } from './form-tabs/extended-profile/institution-details/institution-details.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ServicesComponent } from './dashboard/services/services.component';
import { canDeactivateGuard } from './guards/can-deactivate.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: 'home', component: HomeComponent},
    {path: 'dashboard', redirectTo: 'dashboard/services', pathMatch: 'full'},
    {path: 'dashboard', component: DashboardComponent,
        children: [
          { path: 'services', component: ServicesComponent },
          { path: 'about-us', component: ServicesComponent },
          { path: 'pricing', component: ServicesComponent },
          { path: 'contact-us', component: ServicesComponent }
        ]
    },
    { path: 'file-upload', component: FileUploadComponent },
    { path: 'basic-information', redirectTo: 'basic-information/basic-info', pathMatch: 'full' },
    {
        path: 'basic-information',
        component: BasicInformationComponent,
        children: [
            { path: 'basic-info', component: BasicInfoComponent, canDeactivate: [canDeactivateGuard] },
            { path: 'contact-details', component: ContactDetailsComponent },
          { path: 'recognition-details', component: RecognitionDetailsComponent },
          { path: 'area-location', component: AreaLocationComponent }
        ]
      },
    { path: 'academic-information', redirectTo: 'academic-information/college-info', pathMatch: 'full' },
    {
        path: 'academic-information',
        component: AcademicInformationComponent,
        children: [
            { path: 'college-info', component: CollegeInfoComponent },
            { path: 'teaching-faculty', component: TeachingFacultyComponent },
            { path: 'staff', component: StaffComponent },
            { path: 'enrollment-details', component: EnrollmentDetailsComponent },
            { path: 'extra-details', component: ExtraDetailsComponent }
        ]
    },
    { path: 'extended-profile', redirectTo: 'extended-profile/program-details', pathMatch: 'full' },
    {
        path: 'extended-profile',
        component: ExtendedProfileComponent,
        children: [
            { path: 'program-details', component: ProgramDetialsComponent },
            { path: 'student-details', component: StudentDetailsComponent },
            { path: 'academic-details', component: AcademicDetailsComponent },
            { path: 'institution-details', component: InstitutionDetailsComponent },
        ]
    }
];
