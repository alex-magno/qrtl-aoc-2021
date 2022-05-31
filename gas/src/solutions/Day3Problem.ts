namespace challenges {

    export class day3Problem {

        data: string[];

        constructor(dataUrl: string) {
            this.data = this.getData(dataUrl);
        }

        solve(): Solution {
            const partOneResult = this.solvePartOne(this.data);
            const partTwoResult = this.solvePartTwo(this.data);
            return {
                day: 'd3',
                partOneResult: `<p>Part One result: ${partOneResult}</p>`,
                partTwoResult: `<p>Part Two result: ${partTwoResult}</p>`
            }
        }

        protected solvePartOne(data: string[]): number {
            let [gamaRate, epsilonRate] = ['', ''];
            let binaryLength = data[0].length;
            for (let i = 0; i < binaryLength; i++) {
                let bit = this.getMostCommonBitInColumn(data, i);
                gamaRate += bit;
                bit === '1' ? epsilonRate += '0' : epsilonRate += '1';
            }
            return parseInt(gamaRate, 2) * parseInt(epsilonRate, 2);
        }

        protected solvePartTwo(data: string[]): number {
            let O2Rating = this.selectByCriteria(data, this.getMostCommonBitInColumn, '1');
            let CO2Rating = this.selectByCriteria(data, this.getLeastCommonBitInColumn, '0');
            return parseInt(O2Rating[0], 2) * parseInt(CO2Rating[0], 2);
        }

        private selectByCriteria(data: string[], criteria: Function, tiebreaker?: string): string[] {
            let array = data;
            for (let i = 0; i < data[0].length; i++) {
                let bit = criteria(array, i, tiebreaker);
                if (array.length > 1) {
                    array = array.filter(binary => binary[i] === bit);
                }
            }
            return array;
        }

        private getMostCommonBitInColumn(data: string[], colIndex: number, tiebreaker?: string): string {
            let [zeros, ones] = [0, 0];
            for (const entry of data) {
                entry[colIndex] === '0' ? zeros++ : ones++;
            }
            if (tiebreaker && zeros === ones) {
                return tiebreaker;
            }
            return zeros > ones ? '0' : '1';
        }

        private getLeastCommonBitInColumn(data: string[], colIndex: number, tiebreaker?: string): string {
            let [zeros, ones] = [0, 0];
            for (const entry of data) {
                entry[colIndex] === '0' ? zeros++ : ones++;
            }
            if (tiebreaker && zeros === ones) {
                return tiebreaker;
            }
            return zeros > ones ? '1' : '0';
        }

        private getData(dataUrl: string): string[] {
            const response = UrlFetchApp.fetch(dataUrl);
            return response.getContentText().split('\n');
        }
    }
}
