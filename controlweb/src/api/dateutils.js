
import dayjs, { Dayjs } from 'dayjs';

const dateFormat = 'YYYY-MM-DD HH:mm:ss'
const dateUtils = {

   
    format(date) {
        return dayjs(date).format(dateFormat);
    },

   


}

export default dateUtils