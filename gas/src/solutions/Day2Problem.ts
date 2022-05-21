namespace challenges {

    export class day2Problem {

        data: string[];

        constructor(dataUrl: string) {
            this.data = this.getData(dataUrl);
        }

        solve(): Solution {
            const partOneResult = this.solvePartOne(this.data);
            const partTwoResult = this.solvePartTwo(this.data);
            return {
                day: 'd2',
                partOneResult: `<p>Part One result: ${partOneResult}</p>`,
                partTwoResult: `<p>Part Two result: ${partTwoResult}</p>`
            }
        }

        protected solvePartOne(data: string[]): number {
            let horizontal = 0;
            let vertical = 0;
            for (const entry of data) {
                if (entry.startsWith('forward')) {
                    horizontal += +entry.replace('forward ', '');
                }
                if (entry.startsWith('down')) {
                    vertical += +entry.replace('down ', '');
                }
                if (entry.startsWith('up')) {
                    vertical -= +entry.replace('up ', '');
                }
            }
            return horizontal * vertical;
        }

        protected solvePartTwo(data: string[]): number {
            let horizontal = 0;
            let vertical = 0;
            let aim = 0;
            for (const entry of data) {
                if (entry.startsWith('forward')) {
                    horizontal += +entry.replace('forward ', '');
                    vertical += +entry.replace('forward ', '') * aim;
                }
                if (entry.startsWith('down')) {
                    aim += +entry.replace('down ', '');
                }
                if (entry.startsWith('up')) {
                    aim -= +entry.replace('up ', '');
                }
            }
            return horizontal * vertical;
        }

        private getData(dataUrl: string): string[] {
            const response = UrlFetchApp.fetch(dataUrl);
            return response.getContentText().split('\n');
        }
    }
}
