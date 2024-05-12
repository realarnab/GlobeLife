import { Component } from '@angular/core';
import { PatientService } from '../patient.service';
import { Patient } from '../patient';

@Component({
  selector: 'app-docdash',
  templateUrl: './docdash.component.html',
  styleUrl: './docdash.component.css'
})
export class DocdashComponent {
  constructor(private patientService:PatientService){}
  patients:Patient[]=[];
  searchText: string="";
  ngOnInit():void
  {
    this.getPatients();
  }

  getPatients(){
    this.patientService.getPatientslist().subscribe(data=>{
        this.patients=data;
    })
  }

  searchPatients() {
    if (this.searchText.trim() === '') {
      // If the search text is empty, reset to the original patient list
      this.getPatients();
    } else {
      // Filter patients based on the search text
      this.patients = this.patients.filter(patient =>
        patient.name.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  }

}
