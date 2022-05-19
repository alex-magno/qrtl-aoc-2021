namespace challenges {

    export class day1Problem {

        data: any[];

        constructor() {
            this.data = [];
        }

        solve(): Solution {
            let count = 0
            return {
                challenge: 'd1',
                result: `<p>Result: ${count}</p>`
            }
        }
    }
}
