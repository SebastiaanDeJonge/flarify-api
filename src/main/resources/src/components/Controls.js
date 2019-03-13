import React, { Component } from 'react';
import DatePicker from 'react-datepicker';
import DateHelper from '../utilities/DateHelper';

export default class Controls extends Component {

    static datePicker = {
        dropDownMode: 'select'
    };

    constructor(props) {
        super(props);

        this.state = {
            endDate: props.endDate,
            errorMessage: '',
            startDate: props.startDate
        };

        this.handleStartDateChange = this.handleStartDateChange.bind(this);
        this.handleEndDateChange = this.handleEndDateChange.bind(this);
    }

    handleStartDateChange(date) {

        this.setState({
            errorMessage: DateHelper.validateRange(
                date,
                DateHelper.readableDateToObject(this.state.endDate)
            ),
            startDate: date
        }, () => this.triggerUpdate(
            date,
            DateHelper.readableDateToSimpleDate(this.state.endDate)
        ));
    };

    handleEndDateChange(date) {

        this.setState({
            errorMessage: DateHelper.validateRange(
                DateHelper.readableDateToObject(this.state.startDate),
                date
            ),
            endDate: date
        }, () => this.triggerUpdate(
            DateHelper.readableDateToSimpleDate(this.state.startDate),
            date
        ));
    };

    triggerUpdate = (startDate, endDate) => {
        if (this.state.errorMessage.length === 0) {
            this.props.updateHandler(startDate, endDate);
        }
    };

    render() {
        return (
            <form action="" id="controls" method="post">
                <div className="controls--input">
                    <label htmlFor="startDate">From</label>
                    <DatePicker selected={DateHelper.readableDateToObject(this.state.startDate)}
                                disabledKeyboardNavigation={true}
                                dateFormat={DateHelper.config.format.datePicker}
                                dropdownMode={Controls.datePicker.dropDownMode}
                                minDate={DateHelper.config.minimumDate}
                                maxDate={DateHelper.config.maximumDate}
                                onChange={this.handleStartDateChange}/>
                    {this.state.errorMessage.length > 0 &&
                        <span className="error-label" id="startDateErrorLabel">
                            {this.state.errorMessage}
                        </span>
                    }
                </div>
                <div className="controls--input">
                    <label htmlFor="endDate">Until</label>
                    <DatePicker selected={DateHelper.readableDateToObject(this.state.endDate)}
                                disabledKeyboardNavigation={true}
                                dateFormat={DateHelper.config.format.datePicker}
                                dropdownMode={Controls.datePicker.dropDownMode}
                                minDate={DateHelper.config.minimumDate}
                                maxDate={DateHelper.config.maximumDate}
                                onChange={this.handleEndDateChange}/>
                </div>
            </form>
        )
    }
}
