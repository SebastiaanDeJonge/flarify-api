import moment from 'moment';

export default class DateHelper {

    static config = {
        format: {
            simple: 'YYYY-MM-DD',
            datePicker: 'yyyy-MM-dd',
            readable: 'MMMM Do YYYY',
        },
        minimumDate: moment('2010-04-03', 'YYYY-MM-DD').toDate(),
        maximumDate: moment().toDate()
    };

    static readableDateToObject(date) {

        return moment(date, DateHelper.config.format.readable).toDate();
    }

    static simpleDateToReadableDate(date) {

        return moment(date, DateHelper.config.format.simple).format(DateHelper.config.format.readable);
    }

    static readableDateToSimpleDate(date) {

        return moment(date, DateHelper.config.format.readable).format(DateHelper.config.format.simple);
    }

    static objectToReadableDate(date) {

        return moment(date).format(DateHelper.config.format.readable);
    }

    static validateRange(startDate, endDate) {
        let errorMessage = '';

        console.log('------------------------------');
        console.log(typeof(startDate));
        console.log(typeof(endDate));

        if (startDate < DateHelper.config.minimumDate || endDate < DateHelper.config.minimumDate) {
            errorMessage = 'Unfortunately there is no data available before ' +
                DateHelper.objectToReadableDate(DateHelper.config.minimumDate);
        } else if (startDate > DateHelper.config.maximumDate) {
            errorMessage = 'The start date may not be in the future';
        } else if (endDate > DateHelper.config.maximumDate) {
            errorMessage = 'The end date may not be in the future';
        } else if (endDate <= startDate) {
            errorMessage = 'The start date must occur before the end date.'
        }

        return errorMessage;
    };
}
