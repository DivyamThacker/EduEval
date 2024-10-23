import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-forms-sidebar',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './forms-sidebar.component.html',
  styleUrl: './forms-sidebar.component.css'
})
export class FormsSidebarComponent {
  @Input() subSections: { label: string, route: string }[] = [];
}
