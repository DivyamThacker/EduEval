import { Routes } from '@angular/router';
import { FileUploadComponent } from './file-upload/file-upload.component';

export const routes: Routes = [
    { path: '', redirectTo: '/file-upload', pathMatch: 'full' },
    { path: 'file-upload', component: FileUploadComponent },
    // { path: 'about', component: AboutComponent },
    // { path: 'contact', component: ContactComponent },
    // { path: '**', component: PageNotFoundComponent }
];
