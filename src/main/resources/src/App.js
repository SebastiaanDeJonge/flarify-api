import React, { Component } from 'react';
import ClassificationInfo from './components/ClassificationInfo';
import Controls from './components/Controls';
import Footer from './components/Footer';
import Summary from "./components/Summary";
import DateHelper from './utilities/DateHelper';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import './App.css';

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      summary: {
        startDate: 'January 1st 2016',
        endDate: 'January 1st 2017',
        totalSolarFlares: 27,
        mostPowerful: {
          beginTime: 'July 23rd 2016',
          classType: 'M'
        },
        mostPowerfulPicometre: '7.6E-5',
        mostActiveRegion: {
          region: 12497,
          totalSolarFlares: 6
        }
      }
    };

    this.updateRange = this.updateRange.bind(this);
  }

  updateRange(startDate, endDate) {

    let newSummary = this.state.summary;
    newSummary.startDate = DateHelper.simpleDateToReadableDate(startDate);
    newSummary.endDate = DateHelper.simpleDateToReadableDate(endDate);

    this.setState({summary: newSummary});
  }

  render() {
    return (
        <div className="animation-container">
          <div className="container">
            <header>
              <div className="logo">Flarify</div>
              <div className="controls" id="controls">
                <Controls startDate={this.state.summary.startDate}
                          endDate={this.state.summary.endDate}
                          updateHandler={this.updateRange}/>
              </div>
            </header>

            <div className="content">

              <div className="row">
                <div className="col col-sm-12 col-lg-6" id="summary">
                  <Summary summary={this.state.summary} refresh={this.updateRange}/>
                </div>
                <div className="col col-sm-12 col-lg-6" id="regions">
                </div>
              </div>

              <div className="row">
                <div className="col col-sm-12 col-lg-4" id="classification-info">
                  <ClassificationInfo/>
                </div>
                <div className="col col-sm-12 col-lg-4" id="classification-table">

                </div>
                <div className="col col-sm-12 col-lg-4" id="classification-chart">

                </div>
              </div>

              <div className="row">
                <div className="col col-12" id="footer">
                  <Footer />
                </div>
              </div>
            </div>
          </div>
        </div>
    );
  }
}

export default App;
