import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent {

  constructor(private router: Router) {
  }

  addNewMovie() {
    console.log("add new movie btn clicked ");

    this.router.navigate(["movie/new"]);
  }
  filterMoviesByName(){
    this.router.navigate(["movie/filter"]);
  }
  getMoviesPaginated(){
    this.router.navigate(["movie/paginated"]);
  }
  getMoviesSorted(){
    this.router.navigate(["movie/sorted"]);
  }
}
